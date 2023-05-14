import os
import telebot
from telebot import types
import cv2
import glob
import shutil
from PIL import Image

from imageai.Detection import ObjectDetection

API_TOKEN = ''

bot = telebot.TeleBot(API_TOKEN)


@bot.message_handler(commands=['start'])
def send_welcome(message: types.Message):
    markup = types.ReplyKeyboardMarkup(resize_keyboard=True)
    button = types.KeyboardButton("/menu")
    markup.add(button)
    bot.send_message(message.from_user.id, "Привет!", reply_markup=markup)


@bot.message_handler(commands=['menu'])
def menu(message):
    markup = types.ReplyKeyboardMarkup(resize_keyboard=True)
    btn1 = types.KeyboardButton('Найти животных')
    markup.add(btn1)
    bot.send_message(message.from_user.id, 'Что мне надо сделать?', reply_markup=markup)


@bot.message_handler(content_types=['text'])
def reply(message: types.Message):
    if message.text == 'Найти животных':
        msg = bot.reply_to(message, 'Просто отправь мне видео!')
        bot.register_next_step_handler(msg, find_animals)
    else:
        bot.send_message(message.from_user.id, 'Ты хочешь от меня слишком многого...')


def find_animals(message):
    try:
        bot.send_message(message.from_user.id, 'Видео получено')
        file_details = message.video
        file_id = file_details.file_id
        file_info = bot.get_file(file_id)
        downloaded_video = bot.download_file(file_info.file_path)
        video_file_name = message.video.file_id + ".mp4"
        bot.send_message(message.from_user.id, '2')
        with open(video_file_name, 'wb') as saved_file:
            saved_file.write(downloaded_video)
            new_video_file_name = 'animals.mp4'

        bot.send_message(message.from_user.id, 'Конвертирую видео...')
        convert_mp4_to_jpgs(video_file_name)
        locate_animals(new_video_file_name)
        video = open(new_video_file_name, 'rb')
        bot.send_video(message.from_user.id, video)
        video.close()
        os.remove(video_file_name)
        os.remove(new_video_file_name)
    except:
        bot.send_message(message.from_user.id, 'Ошибка!')


def convert_mp4_to_jpgs(path):
    video_capture = cv2.VideoCapture(path)
    still_reading, image = video_capture.read()
    frame_count = 0
    if os.path.exists("output"):
        # убираем предыдущие frame файлы
        shutil.rmtree("output")
    try:
        os.mkdir("output")
    except IOError:
        print("Error occurred creating output folder")
        return
    # настраиваемые параметры
    seconds = 0.1
    fps = video_capture.get(cv2.CAP_PROP_FPS)  # получаем кадры в секунду
    multiplier = fps * seconds

    while still_reading:
        cv2.imwrite(f"output/frame_{frame_count:05d}.jpg", image)

        # читаем следующее изображение
        frame_id = int(round(video_capture.get(1)))  # текущий номер кадра, округленный
        still_reading, image = video_capture.read()

        if frame_id % multiplier == 0:
            still_reading, image = video_capture.read()
            frame_count += 1


def locate_animals(new_video_file_name, frame_folder="output"):
    images = glob.glob(f"{frame_folder}/*.jpg")
    images.sort()
    frames = [Image.open(image) for image in images]
    frame_one = frames[0]
    execution_path = os.getcwd()
    detector = ObjectDetection()
    detector.setModelTypeAsYOLOv3()
    detector.setModelPath(os.path.join(execution_path, "yolov3.pt"))
    detector.loadModel()
    custom = detector.CustomObjects(bird=True, cat=True, dog=True, horse=True, sheep=True, cow=True,
                                    elephant=True, bear=True, zebra=True, giraffe=True)
    for frame in frames:
        detections = detector.detectCustomObjectsFromImage(custom_object=custom, input_image=frame,  # os.path.join(execution_path, frame)
                                                           output_image_path=frame,  #os.path.join(execution_path, "imagenew.jpg"),
                                                           minimum_percentage_probability=30)
        """
        for eachObject in detections:
            print(eachObject["name"], " : ", eachObject["percentage_probability"], " : ", eachObject["box_points"])
            print("--------------------------------")
           """
    frame_one.save(new_video_file_name, format="MP4", append_images=frames,
                   save_all=True, loop=0)


if __name__ == '__main__':
    bot.polling(none_stop=True, interval=0)

"""
size = 0
number = 0


@bot.message_handler(commands=['start'])
def send_welcome(message: types.Message):
    markup = types.ReplyKeyboardMarkup(resize_keyboard=True)
    button = types.KeyboardButton("/menu")
    markup.add(button)
    bot.send_message(message.from_user.id, "Привет!\nЯ - бот, который конвертирует видео в gif-изображение."
                                           "\nОтправь мне видео, а я сделаю из него гифку:)", reply_markup=markup)


@bot.message_handler(commands=['menu'])
def menu(message):
    markup = types.ReplyKeyboardMarkup(resize_keyboard=True)
    btn1 = types.KeyboardButton('Изменить расширение')
    btn2 = types.KeyboardButton('Изменить частоту кадров')
    btn3 = types.KeyboardButton('Не менять видео')
    markup.add(btn1, btn2, btn3)
    bot.send_message(message.from_user.id, 'Что мне надо сделать?', reply_markup=markup)


@bot.message_handler(commands=['help'])
def send_help(message: types.Message):
    bot.send_message(message.chat.id, "Я конвертирую файлы формата mp4 в файлы формата gif."
                                      "\nДля этого я разбиваю видео на кадры, а потом создаю из них gif анимацию."
                                      "\nЧтобы начать, введи команду '/start' и следуй инструкциям:)")


@bot.message_handler(content_types=['text'])
def reply(message: types.Message):
    if message.text == 'Не менять видео':
        msg = bot.reply_to(message, 'Просто отправь мне видео!')
        bot.register_next_step_handler(msg, handle_video)
    elif message.text == 'Изменить расширение':
        msg = bot.reply_to(message, 'Напиши новое расширение! Например, 600 или 200')
        bot.register_next_step_handler(msg, handle_video_with_resize)
    elif message.text == 'Изменить частоту кадров':
        msg = bot.reply_to(message, 'Напиши, сколько кадров в секунду тебе нужно! Например, 2 или 5')
        bot.register_next_step_handler(msg, handle_video_with_fps)
    else:
        bot.send_message(message.from_user.id, 'Ты хочешь от меня слишком многого...')


def handle_video(message):
    try:
        bot.send_message(message.from_user.id, 'Видео получено')
        file_details = message.video
        file_id = file_details.file_id
        file_info = bot.get_file(file_id)
        downloaded_video = bot.download_file(file_info.file_path)
        video_file_name = message.video.file_id + ".mp4"
        with open(video_file_name, 'wb') as saved_file:
            saved_file.write(downloaded_video)
            gif_file_name = 'gifka.gif'

        bot.send_message(message.from_user.id, 'Конвертирую видео...')
        convert_mp4_to_jpgs(video_file_name)
        make_gif(gif_file_name)
        gif = open(gif_file_name, 'rb')
        bot.send_animation(message.from_user.id, gif)
        gif.close()
        os.remove(video_file_name)
        os.remove(gif_file_name)
    except:
        bot.send_message(message.from_user.id, 'Ошибка!')


def convert_mp4_to_jpgs(path):
    video_capture = cv2.VideoCapture(path)
    still_reading, image = video_capture.read()
    frame_count = 0
    if os.path.exists("output"):
        # убираем предыдущие GIF frame файлы
        shutil.rmtree("output")
    try:
        os.mkdir("output")
    except IOError:
        print("Error occurred creating output folder")
        return

    # настраиваемые параметры
    seconds = 0.1
    fps = video_capture.get(cv2.CAP_PROP_FPS)  # получаем кадры в секунду
    multiplier = fps * seconds

    while still_reading:
        cv2.imwrite(f"output/frame_{frame_count:05d}.jpg", image)

        # читаем следующее изображение
        frame_id = int(round(video_capture.get(1)))  # текущий номер кадра, округленный
        still_reading, image = video_capture.read()

        if frame_id % multiplier == 0:
            still_reading, image = video_capture.read()
            frame_count += 1


def make_gif(gif_path, frame_folder="output"):
    images = glob.glob(f"{frame_folder}/*.jpg")
    images.sort()
    frames = [Image.open(image) for image in images]
    frame_one = frames[0]
    frame_one.save(gif_path, format="GIF", append_images=frames,
                   save_all=True, duration=50, loop=0)


def handle_video_with_resize(message: types.Message):
    global size
    size = message.text.lower()
    msg = bot.send_message(message.chat.id, "Теперь отправь мне видео!")
    bot.register_next_step_handler(msg, handle_and_resize)


def handle_and_resize(message):
    global size
    try:
        bot.send_message(message.from_user.id, 'Видео получено')
        file_details = message.video
        file_id = file_details.file_id
        file_info = bot.get_file(file_id)
        downloaded_video = bot.download_file(file_info.file_path)
        video_file_name = message.video.file_id + ".mp4"
        with open(video_file_name, 'wb') as saved_file:
            saved_file.write(downloaded_video)
            gif_file_name = 'gifka.gif'

        bot.send_message(message.from_user.id, 'Конвертирую видео...')
        os.system(f"ffmpeg -i {video_file_name} -vf scale=size:-1 {video_file_name}")
        convert_mp4_to_jpgs(video_file_name)
        make_gif(gif_file_name)
        gif = open(gif_file_name, 'rb')
        bot.send_animation(message.from_user.id, gif)
        gif.close()
        os.remove(video_file_name)
        os.remove(gif_file_name)
    except:
        bot.send_message(message.from_user.id, 'Ошибка!')


def handle_video_with_fps(message):
    global number
    number = message.text.lower()
    msg = bot.send_message(message.chat.id, "Теперь отправь мне видео!")
    bot.register_next_step_handler(msg, handle_and_change_fps)


def handle_and_change_fps(message):
    try:
        bot.send_message(message.from_user.id, 'Видео получено')
        file_details = message.video
        file_id = file_details.file_id
        file_info = bot.get_file(file_id)
        downloaded_video = bot.download_file(file_info.file_path)
        video_file_name = message.video.file_id + ".mp4"
        with open(video_file_name, 'wb') as saved_file:
            saved_file.write(downloaded_video)
            gif_file_name = 'gifka.gif'

        bot.send_message(message.from_user.id, 'Конвертирую видео...')
        convert_with_fps(video_file_name)
        make_gif(gif_file_name)
        gif = open(gif_file_name, 'rb')
        bot.send_animation(message.from_user.id, gif)
        gif.close()
        os.remove(video_file_name)
        os.remove(gif_file_name)
    except:
        bot.send_message(message.from_user.id, 'Ошибка!')


def convert_with_fps(path):
    global number
    video_capture = cv2.VideoCapture(path)
    still_reading, image = video_capture.read()
    frame_count = 0
    if os.path.exists("output"):
        shutil.rmtree("output")
    try:
        os.mkdir("output")
    except IOError:
        print("Error occurred creating output folder")
        return
    seconds = 0.1
    fps = video_capture.get(number)
    multiplier = fps * seconds

    while still_reading:
        cv2.imwrite(f"output/frame_{frame_count:05d}.jpg", image)

        frame_id = int(round(video_capture.get(1)))
        still_reading, image = video_capture.read()

        if frame_id % multiplier == 0:
            still_reading, image = video_capture.read()
            frame_count += 1


if __name__ == '__main__':
    bot.polling(none_stop=True, interval=0) """
