university-android-task7
========================

Android database

#Задание

Написать приложение, состоящее из трех экранов (Activity):
 1. Главный экран.
 2. Экран Android SQLite
 3. Экран ORMLite.

 Главный экран должен содержать две кнопки, ведущие на экраны (2) и (3).
 Каждый из экранов (2) и (3) должен содержать два фрагмента. В верхнем фрагменте есть поля для ввода текста и кнопки "Insert", "Delete last", которые соответсвенно добавляют и удаляют записи в базе данных. Нижний фрагмент содержит список записей из таблицы и обновляется, после внесения изменений в верхнем фрагменте.

На экране (2) работа с БД должна быть реализована при помощи стандартных средств Android для работы с БД (Content provider, DatabaseOpenHelper etc.)

На экране (3) работа с БД должна быть реализована с применением библиотеки ORMLite (http://ormlite.com/).

Оба экрана (2) и (3) должны позволять работать с одной и той же таблицей БД (данные, добавленные на одном экране должны быть видны на другом).