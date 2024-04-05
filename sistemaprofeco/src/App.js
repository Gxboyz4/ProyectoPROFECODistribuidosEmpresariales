
import './App.css';
//import { PaginaProductos } from './componentes/PaginaProductos';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import {CuerpoRegistrarProfeco} from './componentes/componentesRegistrar/CuerpoRegistrarProfeco';
import {CuerpoLoginProfeco} from './componentes/componentesLogin/CuerpoLoginProfeco'
import {PaginaProductos} from './componentes/componentesBusquedaProductos/PaginaProductos';

const App = () => {
  return (
    <Router>
      <Routes> {/* Envuelve tus rutas dentro de <Routes> */}
        {/* Ruta para el componente de inicio de sesión */}
        <Route path="/" element={<CuerpoLoginProfeco />} />
        {/* Ruta para el componente de registro */}
        <Route path="/registrar" element={<CuerpoRegistrarProfeco />} />
        {/* Ruta para el componente de página productos*/}
       <Route path="/paginaproductos" element={<PaginaProductos />} />
        {/* ... */}
      </Routes>
    </Router>
  );
};

export default App;
