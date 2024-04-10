import './App.css';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import { CuerpoRegistrarSupermercado } from './componentes/componentesRegistrar/CuerpoRegistrarSupermercado';
import { CuerpoRegistrarProducto } from './componentes/componentesRegistroProducto/CuerpoRegistrarProducto';
import { CuerpoLogin } from './componentes/componentesLogin/CuerpoLogin';
import { CuerpoGestionOfertas } from './componentes/componentesOfertas/CuerpoGestionOfertas';

const App = () => {
  return (
    <Router>
      <Routes> {/* Envuelve tus rutas dentro de <Routes> */}
        {/* Ruta para el componente de inicio de sesión */}
        <Route path="/" element={<CuerpoLogin />} />
        {/* Ruta para el componente de registro */}
        <Route path="/registrar" element={<CuerpoRegistrarSupermercado />} />
        {/* Ruta para el componente de página productos*/}
       <Route path="/registroproductos" element={<CuerpoRegistrarProducto />} />
        {/* Ruta para el componente de página gestionar ofertas*/}
        <Route path="/gestionofertas" element={<CuerpoGestionOfertas />} />
        {/* ... */}
      </Routes>
    </Router>
  );
};

export default App;