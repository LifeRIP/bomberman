PGDMP     2                
    z            bomberman_database    15.1    15.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24576    bomberman_database    DATABASE     �   CREATE DATABASE bomberman_database WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Colombia.1252';
 "   DROP DATABASE bomberman_database;
                postgres    false            �            1259    24577    users    TABLE     W   CREATE TABLE public.users (
    name character varying NOT NULL,
    points integer
);
    DROP TABLE public.users;
       public         heap    postgres    false            �          0    24577    users 
   TABLE DATA           -   COPY public.users (name, points) FROM stdin;
    public          postgres    false    214   4       e           2606    32774    users users_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (name);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    214            �   @   x�3537�04611�440ન����1.���4rs2�R�28M���������xNc 'F��� ��     