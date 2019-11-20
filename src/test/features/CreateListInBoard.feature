#language:ru
Свойство: : create lists on dashboard

  @Login
  Сценарий: пользователь создает список на доске
    * он открывает приложение в браузере
    * открывается страница "Главная страница"
    * он (нажимает кнопку) "Войти"
    * открывается страница "Вход в Trello"
    * он (вводит логин и пароль из настроек)
    * он (нажимает кнопку) "Войти"
    * открывается страница "Войдите, чтобы перейти далее"
    * он (вводит логин и пароль из настроек)
    * он (нажимает кнопку) "Войти"
    * открывается страница "Boards"
    * он (создает доску со случайным названием и запоминает его в стек)
    * открывается страница "Board"
    * он (вводит значение в поле) "listName" "Enter list title"
    * он (нажимает кнопку) "Add List"
    * он (проверяет, что существует список с именем) "listName"