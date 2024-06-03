const form = document.getElementById('form');
const campos = document.querySelectorAll('.required');
const spans = document.querySelectorAll('.span-required');

const emailRegex = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

form.addEventListener('submit', (event) => {
    event.preventDefault();

    nameValidate();
    emailValidate();
    pwValidate();
    comparePw();
})

function setError(index) {
    campos[index].style.border = '1px solid #E63636';
    spans[index].style.display = 'block';
}

function removeError(index) {
    campos[index].style.border = '';
    spans[index].style.display = 'none';
}

function nameValidate() {
    if (campos[0].value.length < 3) {
        setError(0);
    } else {
        removeError(0);
    }
}

function emailValidate() {
    if (!emailRegex.test(campos[1].value)) {
        setError(1);
    } else {
        removeError(1);
    }
}

function pwValidate() {
    if (campos[2].value.length < 8) {
        setError(2);
    } else if (!/[A-Z]/.test(campos[2].value)) {
        setError(2);
    } else if (!/[a-z]/.test(campos[2].value)) {
        setError(2);
    } else if (!/[!@#$%^&*(),.?":{}|<>]/.test(campos[2].value)) {
        setError(2);
    } else {
        removeError(2);
        comparePw();
    }
}

function comparePw() {
    if (campos[2].value == campos[3].value && campos[3].value.length >= 8) {
        removeError(3);
    } else {
        setError(3);
    }
}

function createUser() {
    const user = new Object();
    user.name = name = document.getElementsByName('name').value;
    user.email = email = document.getElementById('email').value;
    user.password = pw = document.getElementById('pw').value;

    return user;
}

function generateUser(event) {
    event.preventDefault();

    const url = 'http://localhost:8080/users';

    const user = createUser();
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

    xhr.onload = () => {
        var data = JSON.parse(xhr.responseText);
        if (data.CONFLICT) {
            console.log(data.CONFLICT);
        }
        if (data.CREATED) {
            console.log(data.CREATED);
            location.href = '/HTML/'
        }
    }
    xhr.send(JSON.stringify(user));
}