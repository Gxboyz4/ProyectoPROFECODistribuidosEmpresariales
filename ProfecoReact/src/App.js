
import './App.css';
import { Header } from './componentes/Header';
import { CuerpoPagina } from './componentes/CuerpoPagina';
import { Footer } from './componentes/Footer';


const App = () => {
  return (
    <div>
      <Header />
      <CuerpoPagina />
      <Footer/>
    </div>
  );
};

export default App;
