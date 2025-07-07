import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useWishlist } from './ShopContext';

const RegisterCustomer = () => {

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [isRegistered, setIsRegistered] = useState(false);
    const {getIsUserLoggedIn, loginUser} = useWishlist();
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();
    
        try {
          const response = await fetch('/customer', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, email }),
          });
    
          if (response.ok) {
            loginUser({name, email});
            setIsRegistered(true);
            setTimeout(() => {
                navigate('/');
              }, 2000);
          } else {
            // Handle error response
            const errorData = await response.json();
            console.error('Registraition failed:', errorData.message);
            
          }
        } catch (error) {
          console.error('Registraition failed:', error);
        }
      };


    return (
        <div className="container mt-5">
  <div className="row justify-content-center">
    <div className="col-md-6">
      <div className="card">
        <div className="card-body">
          <h2 className="card-title text-center">Register</h2>
          <form onSubmit={handleRegister}>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Name:
              </label>
              <input
                type="text"
                className="form-control"
                id="name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email:
              </label>
              <input
                type="email"
                className="form-control"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            <button type="submit" className="btn btn-primary">
              Register
            </button>
          </form>
          {isRegistered && (
            <p className="mt-3 alert alert-success">Registration successful!</p>
          )}
        </div>
      </div>
    </div>
  </div>
</div>
      );
}

export default RegisterCustomer;
