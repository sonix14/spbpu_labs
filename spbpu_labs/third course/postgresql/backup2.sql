PGDMP     &                    {            library    15.4    15.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16384    library    DATABASE     {   CREATE DATABASE library WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE library;
                postgres    false            �            1259    16398    BOOKS    TABLE     �   CREATE TABLE public."BOOKS" (
    "ID" integer NOT NULL,
    "NAME" character varying(50) NOT NULL,
    "CNT" integer,
    "TYPE_ID" integer
);
    DROP TABLE public."BOOKS";
       public         heap    postgres    false            �            1259    24614    BOOKS_ID_seq    SEQUENCE     �   ALTER TABLE public."BOOKS" ALTER COLUMN "ID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."BOOKS_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    16401 
   BOOK_TYPES    TABLE     �   CREATE TABLE public."BOOK_TYPES" (
    "ID" integer NOT NULL,
    "NAME" character varying(50) NOT NULL,
    "CNT" integer,
    "FINE" numeric,
    "DAY_COUNT" integer
);
     DROP TABLE public."BOOK_TYPES";
       public         heap    postgres    false            �            1259    24612    BOOK_TYPES_ID_seq    SEQUENCE     �   ALTER TABLE public."BOOK_TYPES" ALTER COLUMN "ID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."BOOK_TYPES_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    16395    CLIENTS    TABLE     8  CREATE TABLE public."CLIENTS" (
    "ID" integer NOT NULL,
    "LAST_NAME" character varying(20) NOT NULL,
    "FIRST_NAME" character varying(20) NOT NULL,
    "FATHER_NAME" character varying(20) NOT NULL,
    "PASSPORT_SERIA" character varying(20) NOT NULL,
    "PASSPORT_NUM" character varying(20) NOT NULL
);
    DROP TABLE public."CLIENTS";
       public         heap    postgres    false            �            1259    24611    CLIENTS_ID_seq    SEQUENCE     �   ALTER TABLE public."CLIENTS" ALTER COLUMN "ID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."CLIENTS_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    16389    JOURNAL    TABLE     �   CREATE TABLE public."JOURNAL" (
    "ID" integer NOT NULL,
    "DATE_BEG" timestamp without time zone,
    "DATE_END" timestamp without time zone,
    "DATE_RET" timestamp without time zone,
    "BOOK_ID" integer,
    "CLIENT_ID" integer
);
    DROP TABLE public."JOURNAL";
       public         heap    postgres    false            �            1259    24613    JOURNAL_ID_seq    SEQUENCE     �   ALTER TABLE public."JOURNAL" ALTER COLUMN "ID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."JOURNAL_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    214                      0    16398    BOOKS 
   TABLE DATA           A   COPY public."BOOKS" ("ID", "NAME", "CNT", "TYPE_ID") FROM stdin;
    public          postgres    false    216   �                 0    16401 
   BOOK_TYPES 
   TABLE DATA           P   COPY public."BOOK_TYPES" ("ID", "NAME", "CNT", "FINE", "DAY_COUNT") FROM stdin;
    public          postgres    false    217   �                 0    16395    CLIENTS 
   TABLE DATA           u   COPY public."CLIENTS" ("ID", "LAST_NAME", "FIRST_NAME", "FATHER_NAME", "PASSPORT_SERIA", "PASSPORT_NUM") FROM stdin;
    public          postgres    false    215   5                  0    16389    JOURNAL 
   TABLE DATA           e   COPY public."JOURNAL" ("ID", "DATE_BEG", "DATE_END", "DATE_RET", "BOOK_ID", "CLIENT_ID") FROM stdin;
    public          postgres    false    214   J!                  0    0    BOOKS_ID_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."BOOKS_ID_seq"', 10, true);
          public          postgres    false    221                       0    0    BOOK_TYPES_ID_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."BOOK_TYPES_ID_seq"', 3, true);
          public          postgres    false    219                       0    0    CLIENTS_ID_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public."CLIENTS_ID_seq"', 9, true);
          public          postgres    false    218                       0    0    JOURNAL_ID_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public."JOURNAL_ID_seq"', 20, true);
          public          postgres    false    220            y           2606    24592    BOOKS BOOKS_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."BOOKS"
    ADD CONSTRAINT "BOOKS_pkey" PRIMARY KEY ("ID");
 >   ALTER TABLE ONLY public."BOOKS" DROP CONSTRAINT "BOOKS_pkey";
       public            postgres    false    216            {           2606    16411    BOOK_TYPES BOOK_TYPES_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public."BOOK_TYPES"
    ADD CONSTRAINT "BOOK_TYPES_pkey" PRIMARY KEY ("ID");
 H   ALTER TABLE ONLY public."BOOK_TYPES" DROP CONSTRAINT "BOOK_TYPES_pkey";
       public            postgres    false    217            w           2606    16407    CLIENTS CLIENTS_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."CLIENTS"
    ADD CONSTRAINT "CLIENTS_pkey" PRIMARY KEY ("ID");
 B   ALTER TABLE ONLY public."CLIENTS" DROP CONSTRAINT "CLIENTS_pkey";
       public            postgres    false    215            u           2606    24620    JOURNAL JOURNAL_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."JOURNAL"
    ADD CONSTRAINT "JOURNAL_pkey" PRIMARY KEY ("ID");
 B   ALTER TABLE ONLY public."JOURNAL" DROP CONSTRAINT "JOURNAL_pkey";
       public            postgres    false    214            ~           2606    24581    BOOKS fk_books_book_types    FK CONSTRAINT     �   ALTER TABLE ONLY public."BOOKS"
    ADD CONSTRAINT fk_books_book_types FOREIGN KEY ("TYPE_ID") REFERENCES public."BOOK_TYPES"("ID") NOT VALID;
 E   ALTER TABLE ONLY public."BOOKS" DROP CONSTRAINT fk_books_book_types;
       public          postgres    false    216    217    3195            |           2606    24593    JOURNAL fk_journal_books    FK CONSTRAINT     �   ALTER TABLE ONLY public."JOURNAL"
    ADD CONSTRAINT fk_journal_books FOREIGN KEY ("BOOK_ID") REFERENCES public."BOOKS"("ID") NOT VALID;
 D   ALTER TABLE ONLY public."JOURNAL" DROP CONSTRAINT fk_journal_books;
       public          postgres    false    216    3193    214            }           2606    24586    JOURNAL fk_journal_clients    FK CONSTRAINT     �   ALTER TABLE ONLY public."JOURNAL"
    ADD CONSTRAINT fk_journal_clients FOREIGN KEY ("CLIENT_ID") REFERENCES public."CLIENTS"("ID") NOT VALID;
 F   ALTER TABLE ONLY public."JOURNAL" DROP CONSTRAINT fk_journal_clients;
       public          postgres    false    3191    215    214               �   x��An�PD��O�T$�az��H]!lXU[�JJ �0�������3��-;�ٳ�Jd!O�[�.��-�Ԟ�]���Aߍ�W��O�]��3>$�P 3��+M.��/_jc����4*��"�z�b*�<��� �JKy�v��P��Kp�k#��M嘶��ג�hn���_J1�S���mT��?;����!�b��         V   x�3�0��Ƌ��/콰�b?�1����������^�raXƈ�,cd�e�ya1P��̅�{�z9��J̹b���� �&(<           x�}PKN�@]�e��]8L�"V�L	�*Bs��aO(t���<�>~vPXa@����.^1�-�}���;de�󥄨����Z��=f �Rb�N�ŭX0�T�+n��-
��9�;�U�Y���5>~)y�gji.uLq�H'hy��(L��N)��3�;�#�:��C��	�#]p�/^���q��egbS%>e11�Lƚie��r��ǭ8Ѽ���������'�7ִ���c������1��OO����O3�         �   x���]1���^����zO��?����֍���1L;IE-Hh�۳^�y-X�,�rz;��j,���D��|�2%.�j;w(�P{'Ԑ ���N�*c���&00�P��~0�ЏS�O�A���ᰋo�`���3ӁeϽ]��4m     