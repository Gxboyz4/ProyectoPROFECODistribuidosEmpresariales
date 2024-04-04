
import './App.css';
//import { PaginaProductos } from './componentes/PaginaProductos';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import {CuerpoRegistrar} from './componentes/componentesRegistrar/CuerpoRegistrar';
import {CuerpoLogin} from './componentes/componentesLogin/CuerpoLogin'
import {PaginaProductos} from './componentes/PaginaProductos';

const App = () => {
  return (
    <Router>
      <Routes> {/* Envuelve tus rutas dentro de <Routes> */}
        {/* Ruta para el componente de inicio de sesión */}
        <Route path="/login" element={<CuerpoLogin />} />
        {/* Ruta para el componente de registro */}
        <Route path="/registrar" element={<CuerpoRegistrar />} />
        {/* Ruta para el componente de página productos*/}
       <Route path="/paginaproductos" element={<PaginaProductos />} />
        {/* ... */}
      </Routes>
    </Router>
  );
};

export default App;
