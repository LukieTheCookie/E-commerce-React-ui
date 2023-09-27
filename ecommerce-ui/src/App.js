import './App.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import RegisterCustomer from './components/RegisterCustomer';
import Product from './components/Product';
import ProductDetials from './components/ProductDetails';
import Basket from './components/Basket';
import Checkout from './components/Checkout';
import PastOrders from './components/PastOrders';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route path='/' Component={Product}/>
          <Route path='/product/:productId' Component={ProductDetials}/>
          <Route path='/register' Component={RegisterCustomer} />
          <Route path='/basket' Component={Basket} />
          <Route path='/cart' Component={Checkout} />
          <Route path='/past-orders' Component={PastOrders} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
