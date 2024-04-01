import React, { useState } from 'react';
import '../estilos/FiltrosBarra.css';

export const FiltrosBarra = ({ onFilterChange, nombreProducto }) => {
    const [supermercado, setSupermercado] = useState('');
    const [categoria, setCategoria] = useState('');

    const manejadorCambioSupermercado = (event) => {
        const newValue = event.target.value;
        const newSupermercado = newValue.replace(/ /g, ".");
        setSupermercado(newSupermercado);
        onFilterChange({ supermercado: newSupermercado, categoria, nombreProducto });
    };

    const manejadorCambioCategoria = (event) => {
        const newValue = event.target.value;
        const newCategoria = newValue.replace(/ /g, ".");
        setCategoria(newCategoria);
        onFilterChange({ supermercado, categoria: newCategoria, nombreProducto });
    };

    return (
        <div className="filtros-barra">
            <div>
                <h3>SUPERMERCADOS</h3>
                <label>
                    <input type="radio" name="supermercado" value="Todos" onChange={manejadorCambioSupermercado} />
                    Todos
                </label>
                <label>
                    <input type="radio" name="supermercado" value="Walmart" onChange={manejadorCambioSupermercado} />
                    Walmart
                </label>
                <label>
                    <input type="radio" name="supermercado" value="Bodega Aurrera" onChange={manejadorCambioSupermercado} />
                    Bodega Aurrera
                </label>
                <label>
                    <input type="radio" name="supermercado" value="Ley" onChange={manejadorCambioSupermercado} />
                    Ley
                </label>
            </div>
            <div>
                <h3>CATEGORÍA</h3>
                <label>
                    <input type="radio" name="categoria" value="Todos" onChange={manejadorCambioCategoria} />
                    Todos
                </label>
                <label>
                    <input type="radio" name="categoria" value="Abarrotes" onChange={manejadorCambioCategoria} />
                    Abarrotes
                </label>
                <label>
                    <input type="radio" name="categoria" value="Frutas y Verduras" onChange={manejadorCambioCategoria} />
                    Frutas y Verduras
                </label>
                <label>
                    <input type="radio" name="categoria" value="Snack" onChange={manejadorCambioCategoria} />
                    Snack
                </label>
            </div>
        </div>
    );
};