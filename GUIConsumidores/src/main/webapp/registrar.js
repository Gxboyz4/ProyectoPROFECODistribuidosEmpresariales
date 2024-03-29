


const btnRegistrar = document.getElementById('btn-registrar');

btnRegistrar.addEventListener('click', async function (event) {
    event.preventDefault();

    const consumidor = obtenerDatosFormulario();
    if (validarFormulario(consumidor)) {
        await registrarConsumidor(consumidor);
    }
});

function obtenerDatosFormulario() {
    var correo = document.getElementById('correo').value;
    var contrasenia = document.getElementById('contrasenia').value;
    var nombre = document.getElementById('nombre').value;
    var apellidoPaterno = document.getElementById('apellidoPaterno').value;
    var apellidoMaterno = document.getElementById('apellidoMaterno').value;
    var estadoResidencia = document.getElementById('estadoResidencia').value;

    return {
        "correo": correo,
        "contrasenia": contrasenia,
        "nombre": nombre,
        "apellidoPaterno": apellidoPaterno,
        "apellidoMaterno": apellidoMaterno,
        "estadoResidencia": estadoResidencia
    };
}


async function registrarConsumidor(consumidor) {
    try {
        const response = await fetch("http://localhost:8080/APIGatewayConsumidores/resources/apiconsumidores/consumidores/registrar/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(consumidor),
        });

        const resultado = await response.json();
        console.log("Consumidor registrado:", resultado);
        limpiarFormulario();
    } catch (error) {
        alert("Ese correo ya esta registrado");
    }
}

function validarFormulario(consumidor) {
    if (
            consumidor.correo.trim() === "" ||
            consumidor.contrasenia.trim() === "" ||
            consumidor.nombre.trim() === "" ||
            consumidor.apellidoPaterno.trim() === "" ||
            consumidor.apellidoMaterno.trim() === "" ||
            consumidor.estadoResidencia.trim() === ""
            ) {
        alert("Por favor completa todos los campos.");
        return false;
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(consumidor.correo)) {
        alert("Por favor ingresa un correo electrónico válido.");
        return false;
    }
    return true;
}

function limpiarFormulario() {
    const correoInput = document.getElementById('correo');
    const contraseniaInput = document.getElementById('contrasenia');
    const nombreInput = document.getElementById('nombre');
    const apellidoPaternoInput = document.getElementById('apellidoPaterno');
    const apellidoMaternoInput = document.getElementById('apellidoMaterno');
    const estadoResidenciaInput = document.getElementById('estadoResidencia');

    correoInput.value = '';
    contraseniaInput.value = '';
    nombreInput.value = '';
    apellidoPaternoInput.value = '';
    apellidoMaternoInput.value = '';
    estadoResidenciaInput.value = '';

    correoInput.focus();
}