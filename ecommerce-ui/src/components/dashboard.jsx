import React from "react";
import { Link } from "react-router-dom";
import "./dashboard.css";

export const Dashboard = () => {
    return (
        <div className="dashboard">
            MoMo's Amazingness
            <div className="routing">
                <Link to="/">Shop</Link>
                <Link to="/cart">Cart</Link>
            </div>
        </div>
    );
};