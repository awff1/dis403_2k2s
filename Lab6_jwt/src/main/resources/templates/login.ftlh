<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Тест</title>
    <style>
        #booking_table span {
            color: blue;
            text-decoration: underline;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div id="login_section" style="display:block">
<form id="form" method="post">
    <div><input name="username" type="text" placeholder="username"/></div>
    <div><input name="password" type="password" placeholder="password"/></div>
    <div><input type="button" value="OK" onclick="login()"/></div>
</form>
</div>

<div id="content_section" style="display:none">
    <h>Данные по бронированиям в гостинице:</h>
    <div>
        <table id="booking_table">

        </table>
    </div>
</div>

<div id="edit_section" style="display:none; margin-top: 20px; border-top: 1px solid #ccc; padding-top: 10px;">
    <h3>Редактирование бронирования</h3>
    <form id="edit_form">
        <input type="hidden" id="edit_id" name="id">

        <div>Дата заезда: <input type="text" id="edit_arrival" name="arrivaldate"></div>
        <div>Дата рождения: <input type="text" id="edit_birthday" name="birthdate"></div>
        <div>Гендер: <input type="text" id="edit_gender" name="gender"></div>
        <div>Номер отеля: <input type="text" id="edit_hotelid" name="hotelId"></div>
        <div>Имя: <input type="text" id="edit_name" name="name"></div>
        <div>Комната: <input type="text" id="edit_room" name="room"></div>
        <div>Дата выезда: <input type="text" id="edit_staying" name="stayingdate"></div>

        <div style="margin-top:10px;">
            <input type="button" value="Сохранить изменения" onclick="save_booking()"/>
        </div>
    </form>
</div>

<script>
    let jwt_token;
    function login() {

        const formDataToJson = (formData) => JSON.stringify(Object.fromEntries(formData));
        const formElement = document.getElementById('form');
        const jsonData = formDataToJson(new FormData(formElement));
        console.log(jsonData)

        fetch('http://localhost:8090/api/auth/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json;charset=utf-8'
          },
          body: jsonData
        })
        .then(response => response.json())
        .then(json => {
            let token = json.token;
            jwt_token = token;
            //localStorage.setItem('jwt_token', token);
            //window.location = '/app';
            document.getElementById("login_section").style.display="none";
            document.getElementById("content_section").style.display="block";

            show_content();
        });
    }

    function show_content() {
        show_bookings();
    }

    function show_bookings() {
        fetch('http://localhost:8090/api/booking/allview', {
          method: 'GET',
          headers: {
            'Authorization': 'Bearer ' + jwt_token
          }
        })
        .then(response => response.json())
        .then(json => {
               make_booking_table(json);
        });
    }

    function make_booking_table(data) {
        let tbl = document.getElementById("booking_table");
       for (let i = 0; i < data.bookings.length; i++) {
          let tr = document.createElement('tr');
          let td_room = document.createElement('td');
          td_room.innerHTML = "<span onclick='open_booking("
                + data.bookings[i].id + ")'>" + data.bookings[i].room + "</span>";
          tr.append(td_room);
          let td_arrivaldate = document.createElement('td');
          td_arrivaldate.innerHTML = data.bookings[i].arrivaldate;
          tr.append(td_arrivaldate);
          let td_stayingdate = document.createElement('td');
          td_stayingdate.innerHTML = data.bookings[i].stayingdate;
          tr.append(td_stayingdate);

          tbl.append(tr);
       }
    }

    function open_booking(booking_id) {
        fetch('http://localhost:8090/api/booking/get/' + booking_id, {
          method: 'GET',
          headers: {
            'Authorization': 'Bearer ' + jwt_token
          }
        })
        .then(response => response.json())
        .then(json => {

              fill_booking(json);
        });
    }

    function fill_booking(data) {
        document.getElementById("edit_section").style.display = "block";
        console.log("ДАННЫЕ С СЕРВЕРА===================", data);
        // Заполняем поля значениями
        document.getElementById("edit_arrival").value = data.arrivaldate;
        document.getElementById("edit_birthday").value = data.birthdate;
        document.getElementById("edit_gender").value = data.gender;
        document.getElementById("edit_hotelid").value = data.hotelId;
        document.getElementById("edit_name").value = data.name;
        document.getElementById("edit_room").value = data.room;
        document.getElementById("edit_staying").value = data.stayingdate;

        document.getElementById("edit_id").value = data.id;
    }

    function save_booking() {
        const bookingId = document.getElementById("edit_id").value;

        // Собираем объект так, чтобы имена полей СОВПАДАЛИ с именами в BookingPersopnViewDto.java
        const data = {
            id: bookingId,
            arrivaldate: document.getElementById("edit_arrival").value,
            birthdate: document.getElementById("edit_birthday").value,
            gender: document.getElementById("edit_gender").value,
            hotelId: document.getElementById("edit_hotelid").value,
            name: document.getElementById("edit_name").value,
            room: document.getElementById("edit_room").value,
            stayingdate: document.getElementById("edit_staying").value
        };

        fetch('http://localhost:8090/api/booking/update/' + bookingId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
                'Authorization': 'Bearer ' + jwt_token // Проверь, что jwt_token не пустой
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (response.ok) {
                alert("Сохранено на сервере!");
                document.getElementById("edit_section").style.display = "none";
                document.getElementById("booking_table").innerHTML = ""; // Очищаем старую таблицу
                show_bookings(); // Загружаем обновленный список
            } else {
                alert("Ошибка: " + response.status + ". Проверь консоль.");
            }
        })
        .catch(err => console.error("Ошибка запроса:", err));
    }
</script>


</body>
</html>