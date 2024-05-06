import './App.css';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import { CuerpoRegistrarSupermercado } from './componentes/componentesRegistrar/CuerpoRegistrarSupermercado';
import { CuerpoRegistrarProducto } from './componentes/componentesRegistroProducto/CuerpoRegistrarProducto';
import { CuerpoLogin } from './componentes/componentesLogin/CuerpoLogin';
import { CuerpoGestionOfertas } from './componentes/componentesOfertas/CuerpoGestionOfertas';
import { CuerpoMenu } from './componentes/componentesMenu/CuerpoMenu';

const App = () => {
  const sesion = localStorage.getItem('token');

  return (
    <Router>
      <Routes> {/* Envuelve tus rutas dentro de <Routes> */}
        <Route path="/" element={sesion ? <Navigate to="/menu" /> : <Navigate to="/login" />} />
        {/* Ruta para el componente de inicio de sesión */}
        <Route path="/login" element={<CuerpoLogin />} />
        {/* Ruta para el componente de registro */}
        <Route path="/registrar" element={<CuerpoRegistrarSupermercado />} />
        {/* Ruta para el componente de página productos*/}
        <Route path="/registroproductos" element={<CuerpoRegistrarProducto />} />
        {/* Ruta para el componente de página gestionar ofertas*/}
        <Route path="/gestionofertas" element={<CuerpoGestionOfertas />} />
        {/* Ruta para el componente de página menú*/}
        <Route path="/menu" element={<CuerpoMenu />} />
        {/* ... */}
      </Routes>
    </Router>
  );
};

export default App;