import React from 'react';
import { Link } from 'react-router-dom';
import { useContext } from 'react';
import { CartContext } from '../Context/CartContext';
import './Navbar.css'; 

function Navbar() {
    const { cart } = useContext(CartContext);

    return (
        <nav className="navbar">
            <div className="logo">
                <Link to="/">🛍️ E-Shop</Link>
            </div>
            <ul className="nav-links">
                <li><Link to="/">Home</Link></li>
                <li><Link to="/cart">Cart <span className="cart-count">{cart.length}</span></Link></li>
            </ul>
        </nav>
    );
}

export default Navbar;
