import React, { useContext } from 'react';
import { CartContext } from '../Context/CartContext';
import './CartPage.css'; 

function CartPage() {
    const { cart, removeFromCart } = useContext(CartContext);

    // 🔹 Calculate total price
    const totalPrice = cart.reduce((acc, item) => acc + item.price, 0);

    return (
        <div className="cart-container">
            <h1>Your Shopping Cart 🛒</h1>
            
            {cart.length === 0 ? (
                <p className="empty-cart">Your cart is empty.</p>
            ) : (
                <>
                    <table className="cart-table">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Price</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>
                            {cart.map((item) => (
                                <tr key={item.id}>
                                    <td>{item.name}</td>
                                    <td>${item.price}</td>
                                    <td>
                                        <button className="remove-btn" onClick={() => removeFromCart(item.id)}>❌</button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    {/* 🔹 Show Total Price */}
                    <div className="total-price">
                        <h2>Total: ${totalPrice.toFixed(2)}</h2>
                    </div>
                </>
            )}
        </div>
    );
}

export default CartPage;
