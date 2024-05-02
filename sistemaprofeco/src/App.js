import './App.css';
import { PaginaProductos } from "./componentes/componentesBusquedaProductos/PaginaProductos";
import { useAuth0 } from "@auth0/auth0-react"
import { LoginButton } from './componentes/componentesAutentication/LoginButton';

const App = () => {
  const { isAuthenticated} = useAuth0();

  return (
    <div className="App">
      {isAuthenticated ? <PaginaProductos /> : <LoginButton />}
    </div>
  );
}

export default App;