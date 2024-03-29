/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const btnRegistrar = document.getElementById('btn-registrar');

btnRegistrar.addEventListener('click', async function (event) {
    event.preventDefault();

    if (obtenerDatosFormulario() !== null) {
        const supermercado = obtenerDatosFormulario();
        if (validarFormulario(supermercado)) {
            await registrarSupermercado(supermercado);
        }
    }
});

function obtenerDatosFormulario() {
    var correo = document.getElementById('correo').value;
    var contrasenia = document.getElementById('contrasenia').value;
    var nombre = document.getElementById('nombre').value;
    var calle = document.getElementById('calle').value;
    var codigoPostal = document.getElementById('codigoPostal').value;
    var colonia = document.getElementById('colonia').value;

    if (correo.trim() === "" || contrasenia.trim() === "" || nombre.trim() === "") {
        alert("Por favor completa todos los campos.");
        return null;
    }

    if (calle.trim() === "" || codigoPostal.trim() === "" || colonia.trim() === "") {
        alert("Por favor completa todos los campos de la dirección.");
        return null;
    }

    return {
        "correo": correo,
        "contrasenia": contrasenia,
        "nombre": nombre,
        "direccion": calle + ", " + codigoPostal + ", " + colonia
    };
}


async function registrarSupermercado(supermercado) {
    try {
        const response = await fetch("http://localhost:8080/APIGatewaySupermercados/resources/apisupermercados/supermercados/registrar/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(supermercado),
        });

        const resultado = await response.json();
        console.log("Supermercado registrado:", resultado);
        limpiarFormulario();
    } catch (error) {
        alert("Ese correo ya esta registrado");
    }
}

function limpiarFormulario() {
    const correoInput = document.getElementById('correo');
    const contraseniaInput = document.getElementById('contrasenia');
    const nombreInput = document.getElementById('nombre');
    const calleInput = document.getElementById('calle');
    const codigoPostalInput = document.getElementById('codigoPostal');
    const coloniaInput = document.getElementById('colonia');

    correoInput.value = '';
    contraseniaInput.value = '';
    nombreInput.value = '';
    calleInput.value = '';
    codigoPostalInput.value = '';
    coloniaInput.value = '';

    correoInput.focus();
}

function validarFormulario(supermercado) {
    if (
            supermercado.correo.trim() === "" ||
            supermercado.contrasenia.trim() === "" ||
            supermercado.nombre.trim() === "" ||
            supermercado.direccion.trim() === ""
            ) {
        alert("Por favor completa todos los campos.");
        return false;
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(supermercado.correo)) {
        alert("Por favor ingresa un correo electrónico válido.");
        return false;
    }
    return true;
}

