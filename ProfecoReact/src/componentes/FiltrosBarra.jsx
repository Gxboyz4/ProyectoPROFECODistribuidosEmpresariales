import React from 'react';
import '../estilos/FiltrosBarra.css';

export const FiltrosBarra = () => {
    return (
        <div className="filtros-barra">
            <div>
                <h3>SUPERMERCADOS</h3>
                <label>
                    <input type="checkbox" name="supermercado" value="Walmart" />
                    Walmart
                </label>
                <label>
                    <input type="checkbox" name="supermercado" value="Bodega Aurrera" />
                    Bodega Aurrera
                </label>
                <label>
                    <input type="checkbox" name="supermercado" value="Ley" />
                    Ley
                </label>
            </div>
            <div>
                <h3>CATEGORÍA</h3>
                <label>
                    <input type="checkbox" name="categoria" value="abarrotes" />
                    Abarrotes
                </label>
                <label>
                    <input type="checkbox" name="categoria" value="frutas_y_verduras" />
                    Frutas y Verduras
                </label>
                <label>
                    <input type="checkbox" name="categoria" value="snacks" />
                    Snacks
                </label>
            </div>
        </div>
    );
};