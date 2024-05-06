import React, { useState, useEffect } from 'react';
import ReactModal from "react-modal";
import "../../estilos/estilosInconsistencias/frmInconsistencias.css";
import { useAuth0 } from "@auth0/auth0-react"

export const FrmInconsistencias = ({idSupermercado, idProducto}) => {
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [descripcion, setDescripcion] = useState('');
    const [token, setToken] = useState(null);
    const { isAuthenticated, getAccessTokenSilently } = useAuth0();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = await getAccessTokenSilently();
                setToken(token);
            } catch (error) {
            }
        };

        if (isAuthenticated) {
            fetchData();
        }
    }, [getAccessTokenSilently, isAuthenticated]);

    const openModal = () => {
        setModalIsOpen(true);
    };

    const closeModal = () => {
        setModalIsOpen(false);
    };

    const handleInputChange = (event) => {
        setDescripcion(event.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const nuevaInconsistencia = {
            descripcion,
            idSupermercado
        };

        try {
            const response = await fetch('http://localhost:8080/APIGatewayConsumidores/resources/apiconsumidores/inconsistencias/publicar/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token == null ? await getAccessTokenSilently() : token}`
                },
                body: JSON.stringify(nuevaInconsistencia)
            });

            if (response.ok) {
                setDescripcion("")
            } 
        } catch (error) {
            console.error('Error al registrar la inconsistencia:', error);
        }
    };

    return (
        <div>
            <button onClick={openModal}>Añadir una queja</button>
            <ReactModal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
            >
                <h2>¿Por qué tienes una queja?</h2>
                <form onSubmit={handleSubmit}>
                    <textarea
                        value={descripcion}
                        onChange={handleInputChange}
                        placeholder="Escribe aquí tu queja..."
                        rows="4"
                        cols="20"
                    />
                    <br />
                    <button type="submit">Enviar</button>
                    <button onClick={closeModal}>Cancelar</button>
                </form>
            </ReactModal>
        </div>
    );
};
