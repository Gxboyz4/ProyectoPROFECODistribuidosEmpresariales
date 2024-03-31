import React, { useState } from 'react';
import '../estilos/FiltrosBarra.css';

export const FiltrosBarra = ({ onFilterChange }) => {
    const [supermercado, setSupermercado] = useState('');
    const [categoria, setCategoria] = useState('');

    const handleSupermercadoChange = (event) => {
        let valor = event.target.value;
        let nuevaCadena = valor.replace(/ /g, ".");
        setSupermercado(nuevaCadena);
        onFilterChange({ supermercado: nuevaCadena, categoria });
    };

    const handleCategoriaChange = (event) => {
        let valor = event.target.value;
        let nuevaCadena = valor.replace(/ /g, ".");
        setCategoria(nuevaCadena);
        onFilterChange({ supermercado, categoria: nuevaCadena });
    };

    return (
        <div className="filtros-barra">
            <div>
                <h3>SUPERMERCADOS</h3>
                <label>
                    <input type="radio" name="supermercado" value="Walmart" onChange={handleSupermercadoChange} />
                    Walmart
                </label>
                <label>
                    <input type="radio" name="supermercado" value="Bodega Aurrera" onChange={handleSupermercadoChange} />
                    Bodega Aurrera
                </label>
                <label>
                    <input type="radio" name="supermercado" value="Ley" onChange={handleSupermercadoChange} />
                    Ley
                </label>
            </div>
            <div>
                <h3>CATEGORÍA</h3>
                <label>
                    <input type="radio" name="categoria" value="Abarrotes" onChange={handleCategoriaChange} />
                    Abarrotes
                </label>
                <label>
                    <input type="radio" name="categoria" value="Frutas y Verduras" onChange={handleCategoriaChange} />
                    Frutas y Verduras
                </label>
                <label>
                    <input type="radio" name="categoria" value="Snack" onChange={handleCategoriaChange} />
                    Snack
                </label>
            </div>
        </div>
    );
};