import React, { useState, useEffect } from "react";
import { getOrders, getProductById } from "../service/api";

const PastOrders = () => {
    const [orders, setOrders] = useState([]);
    const [filterValue, setFilterValue] = useState('');
  
    useEffect(() => {
      fetchOrders();
    }, []);
  
    useEffect(() => {
      handleFilter();
    }, [filterValue]);
  
    const fetchOrders = async () => {
      try {
        const response = await getOrders();
        if (response && Array.isArray(response)) {
          const filteredOrders = response.filter(
            (order) => order.products !== null && order.customer_id !== null
          );
          setOrders(filteredOrders);
        } else {
          // Handle the case when the response is not an array
          setOrders([]);
        }
      } catch (error) {
        console.error('Error fetching orders:', error);
        setOrders([]);
      }
    };
  
    const handleFilter = () => {
      const filteredOrders = orders.filter((order) =>
        (order.products !== null && order.customer_id !== null) &&
        (order.id.toString().includes(filterValue) || order.customer_id.toString().includes(filterValue))
      );
      setOrders(filteredOrders);
    };
  
    const resetTable = () => {
      setFilterValue('');
      fetchOrders();
    };
  
    return (
      <div className="container mt-4">
        <h1 className="display-4">Past Orders</h1>
        <div className="mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Filter by Order ID or Customer ID"
            value={filterValue}
            onChange={(e) => setFilterValue(e.target.value)}
          />
          <button
            className="btn btn-primary mt-2"
            onClick={handleFilter}
          >
            Filter
          </button>
          <button
            className="btn btn-secondary mt-2 ml-2"
            onClick={resetTable}
          >
            Reset
          </button>
        </div>
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>Order ID</th>
              <th>Customer ID</th>
              <th>Total</th>
              <th>Products</th>
            </tr>
          </thead>
          <tbody>
            {orders.map((order) => (
              <tr key={order.id}>
                <td>{order.id}</td>
                <td>{order.customer_id}</td>
                <td>R{order.total}</td>
                <td>
                <ul>
                  {order.products.map((productId) => (
                    <li key={productId}>{productId}</li>
                  ))}
                </ul>
              </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  };
  
  export default PastOrders;