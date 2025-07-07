import React, { useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { useWishlist } from './ShopContext';

const Login = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const {getIsUserLoggedIn, loginUser} = useWishlist();
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('/customer/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, email }),
      });

      if (response.ok) {
        loginUser({name, email});
        setTimeout(() => {
            navigate('/');
          }, 2000);
      } else {
        // Handle error response
        const errorData = await response.json();
        console.error('Login failed:', errorData.message);
        
      }
    } catch (error) {
      console.error('Login failed:', error);
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h2 className="card-title text-center">Login</h2>
              <form onSubmit={handleLogin}>
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
                <div className="d-flex justify-content-between">
                  <button type="submit" className="btn btn-primary">
                    Login
                  </button>
                  <NavLink to='/register' className="btn btn-outline-dark ms-2">
                    <i className="fa fa-user-plus me-1"></i>Create Account
                  </NavLink>
                </div>
              </form>
              {getIsUserLoggedIn() && (
                <p className="mt-3 alert alert-success">Login successful! Redirecting...</p>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
