import './App.css';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import { CuerpoRegistrar } from './componentes/componentesRegistrar/CuerpoRegistrar';
import { CuerpoRegistrarProducto } from './componentes/componentesRegistroProducto/CuerpoRegistrarProducto';
import { CuerpoLogin } from './componentes/componentesLogin/CuerpoLogin';

const App = () => {
  return (
    <Router>
      <Routes> {/* Envuelve tus rutas dentro de <Routes> */}
        {/* Ruta para el componente de inicio de sesión */}
        <Route path="/" element={<CuerpoLogin />} />
        {/* Ruta para el componente de registro */}
        <Route path="/registrar" element={<CuerpoRegistrar />} />
        {/* Ruta para el componente de página productos*/}
       <Route path="/registroproductos" element={<CuerpoRegistrarProducto />} />
        {/* ... */}
      </Routes>
    </Router>
  );
};

export default App;