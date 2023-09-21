import './App.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Dashboard } from './components/dashboard';

function App() {
  return (
    <div className="App">
      <Router>
        <Dashboard />
        <Routes>
          <Route path='/' />
          <Route path='/cart' />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
