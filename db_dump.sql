PGDMP  %    !                }            seta    16.8    16.8 '    G           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            H           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            I           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            J           1262    16496    seta    DATABASE     j   CREATE DATABASE seta WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'pt-BR';
    DROP DATABASE seta;
                postgres    false            �            1259    16512    access_logs    TABLE     �   CREATE TABLE public.access_logs (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    type smallint NOT NULL,
    access_date_time timestamp without time zone NOT NULL
);
    DROP TABLE public.access_logs;
       public         heap    postgres    false            �            1259    16511    access_logs_id_seq    SEQUENCE     {   CREATE SEQUENCE public.access_logs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.access_logs_id_seq;
       public          postgres    false    218            K           0    0    access_logs_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.access_logs_id_seq OWNED BY public.access_logs.id;
          public          postgres    false    217            �            1259    16532    mail_extensions    TABLE     �   CREATE TABLE public.mail_extensions (
    id bigint NOT NULL,
    mail_extension character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now()
);
 #   DROP TABLE public.mail_extensions;
       public         heap    postgres    false            �            1259    16531    mail_extensions_id_seq    SEQUENCE        CREATE SEQUENCE public.mail_extensions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.mail_extensions_id_seq;
       public          postgres    false    220            L           0    0    mail_extensions_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.mail_extensions_id_seq OWNED BY public.mail_extensions.id;
          public          postgres    false    219            �            1259    16570    new_account_requests    TABLE     �   CREATE TABLE public.new_account_requests (
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    token character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT now()
);
 (   DROP TABLE public.new_account_requests;
       public         heap    postgres    false            �            1259    16556    supports    TABLE       CREATE TABLE public.supports (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    phone_number character varying(20) NOT NULL,
    subject character varying(255) NOT NULL,
    message text NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.supports;
       public         heap    postgres    false            �            1259    16555    supports_id_seq    SEQUENCE     x   CREATE SEQUENCE public.supports_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.supports_id_seq;
       public          postgres    false    222            M           0    0    supports_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.supports_id_seq OWNED BY public.supports.id;
          public          postgres    false    221            �            1259    16498    users    TABLE     m  CREATE TABLE public.users (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role smallint DEFAULT 2 NOT NULL,
    qrcode_type smallint,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now()
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16497    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    216            N           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    215            �           2604    16515    access_logs id    DEFAULT     p   ALTER TABLE ONLY public.access_logs ALTER COLUMN id SET DEFAULT nextval('public.access_logs_id_seq'::regclass);
 =   ALTER TABLE public.access_logs ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    16535    mail_extensions id    DEFAULT     x   ALTER TABLE ONLY public.mail_extensions ALTER COLUMN id SET DEFAULT nextval('public.mail_extensions_id_seq'::regclass);
 A   ALTER TABLE public.mail_extensions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    16559    supports id    DEFAULT     j   ALTER TABLE ONLY public.supports ALTER COLUMN id SET DEFAULT nextval('public.supports_id_seq'::regclass);
 :   ALTER TABLE public.supports ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    16501    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            ?          0    16512    access_logs 
   TABLE DATA           J   COPY public.access_logs (id, user_id, type, access_date_time) FROM stdin;
    public          postgres    false    218   �,       A          0    16532    mail_extensions 
   TABLE DATA           U   COPY public.mail_extensions (id, mail_extension, created_at, updated_at) FROM stdin;
    public          postgres    false    220   -       D          0    16570    new_account_requests 
   TABLE DATA           N   COPY public.new_account_requests (email, name, token, created_at) FROM stdin;
    public          postgres    false    223   �-       C          0    16556    supports 
   TABLE DATA           [   COPY public.supports (id, user_id, phone_number, subject, message, created_at) FROM stdin;
    public          postgres    false    222   �.       =          0    16498    users 
   TABLE DATA           e   COPY public.users (id, name, email, password, role, qrcode_type, created_at, updated_at) FROM stdin;
    public          postgres    false    216   {0       O           0    0    access_logs_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.access_logs_id_seq', 1, false);
          public          postgres    false    217            P           0    0    mail_extensions_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.mail_extensions_id_seq', 14, true);
          public          postgres    false    219            Q           0    0    supports_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.supports_id_seq', 10, true);
          public          postgres    false    221            R           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 54, true);
          public          postgres    false    215            �           2606    16517    access_logs access_logs_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.access_logs
    ADD CONSTRAINT access_logs_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.access_logs DROP CONSTRAINT access_logs_pkey;
       public            postgres    false    218            �           2606    16541 2   mail_extensions mail_extensions_mail_extension_key 
   CONSTRAINT     w   ALTER TABLE ONLY public.mail_extensions
    ADD CONSTRAINT mail_extensions_mail_extension_key UNIQUE (mail_extension);
 \   ALTER TABLE ONLY public.mail_extensions DROP CONSTRAINT mail_extensions_mail_extension_key;
       public            postgres    false    220            �           2606    16539 $   mail_extensions mail_extensions_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.mail_extensions
    ADD CONSTRAINT mail_extensions_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.mail_extensions DROP CONSTRAINT mail_extensions_pkey;
       public            postgres    false    220            �           2606    16577 .   new_account_requests new_account_requests_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.new_account_requests
    ADD CONSTRAINT new_account_requests_pkey PRIMARY KEY (email);
 X   ALTER TABLE ONLY public.new_account_requests DROP CONSTRAINT new_account_requests_pkey;
       public            postgres    false    223            �           2606    16564    supports supports_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.supports
    ADD CONSTRAINT supports_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.supports DROP CONSTRAINT supports_pkey;
       public            postgres    false    222            �           2606    16510    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    216            �           2606    16508    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    216            �           2606    16518 $   access_logs access_logs_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.access_logs
    ADD CONSTRAINT access_logs_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.access_logs DROP CONSTRAINT access_logs_user_id_fkey;
       public          postgres    false    218    216    4768            �           2606    16565    supports fk_support_user    FK CONSTRAINT     �   ALTER TABLE ONLY public.supports
    ADD CONSTRAINT fk_support_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.supports DROP CONSTRAINT fk_support_user;
       public          postgres    false    4768    222    216            ?      x������ � �      A   �   x�}�K�@D��)�����3+���"��%$DECһWy]EYy����q���鞱c;��('IΒ��2� �UT��&�.��"�����_���[�m~���(�玓hr�
�1+�a��g�%&����4j��z��9�����
�ApD x��L�      D   �   x�E�1n�0��>�.@��$��dth�E�.�,�pu���k�% ���3���ｔ}�4mZ�!�[���{F棦�U��qd^���މW9y�.�I\\QX9a�X��) ��+�)��%��m9��>���O�2FF��<x�Z!�l��jX���!��'o� ^�6��s�o��Ws�[���v��eQ��ϱ@t���h�K�=q6H��솋�C�9�}�@T      C   �  x��S���0����2b�/=��H��!0R�ٓh��D
$���&H�;�cY��wy�Aڥfv�C�{%�kh���a��?f�����c��<t�E����GNp���V3�e��tk༕�u�.���X�&&t�����;Q�E��a� #��q���)F��G�x8~ֿ�V��<�R����L.�Fe�>��F ��6���y�0y0n�H������v��dY���~IY���R*��L3��)9]O�z�'*���f@x0��}�/ y>_�@�g<���ȳ�5�>-Ћ�1Rk]F$O�~���0��L�jU٪:/��Je�?E��xV����v���U���9�UUd%���QWe��J���0q�4#�l�.�I���&؝��邡�Б���8�2��&����z,��UM^5eY7ٗ<˲��V!�      =   �  x�}�Iw�:F��W�ȶe�V�<�!m�$�m�d+�`����?ٝ�3t���osϭRaÕa��̜iZ��I�}����L�>�@Tl�,o�9ף�������g�h[��~<_5�Y��>Xt�>�'v�/�ߩ�ƀ������؄�a�ap����#�0�0@L�5b4e�~���}A��`YAX���bp��b9��6h����Y���Mv���
�Zjzߙ�'�z�����Y0a	aYo�&�RC�Ծ(_Ў꨾�*�m�����
Az��y����[��+��;p:�m�Mgˍ�q�O��/P�AXsQB�[Ԅ�!�l��5�u)�jK��W���*����z�W~0g�q�����蓹��۲�n뤶��x��21v(r� a\��Hԁp,����ђeVT�����ʈ��z-|�Ӈ�qx���ZW�W��Ea��b� �d~KIf���,d^#mŧ�
r�3`3���A�-`]�P��FW�X��*L�V�O�}�^�:mK1i��O�o�?Z$1;7��G��gq:{;�WwF�j��8���[
m�1�!�9�!ty�R.�BK+T�֦�P~m)�E}+H�7h��o;�a?r����o����wdъ�2>��B���_x����ٌ�D5
���/+��ʍ�3NL���V��\�}�V/��;�,��ql��k	���������ֿ;T]7^��S�L����k�^�.�����1B�F�1<D�2Y���. ����ig��'ޫC��,
p����t��y�l+�GI"�f�����@o���u�KA6���7��j�����7���$��W���7���h-�����sa�������uc��쨥��]��q���"���$�����D��@�V�<�O     