import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";

const BookTicket = () => {
    const { showId } = useParams();
    const navigate = useNavigate();
    const [seats, setSeats] = useState(1);
    const [message, setMessage] = useState("");

    const handleBooking = async () => {
        const token = localStorage.getItem("token");

        if (!token) {
            navigate("/login");
            return;
        }

        try {
            await axios.post(
                "http://localhost:8090/book",
                { showId: parseInt(showId), seatsBooked: seats },
                {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
            setMessage("🎟 Booking Confirmed! Enjoy your show.");
            setTimeout(() => navigate("/"), 2000); 
        } catch (error) {
            console.error("Booking failed:", error);
            setMessage("❌ Booking failed! Please try again.");
        }
    };

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="card p-4 shadow-lg rounded-4 border-0" style={{ maxWidth: "420px", width: "100%" }}>
                <h2 className="fw-bold text-center text-primary">🎟 Book Your Ticket</h2>
                <p className="text-center text-muted mb-4">Secure your seat now!</p>

                <div className="bg-light p-3 rounded-3">
                    <p className="fw-semibold mb-2">Show ID: <span className="text-primary">{showId}</span></p>
                    <label className="fw-semibold mb-1">Seats to Book:</label>
                    <div className="input-group mb-3">
                        <button 
                            className="btn btn-outline-secondary" 
                            onClick={() => setSeats(Math.max(1, seats - 1))}
                        >−</button>
                        <input
                            type="number"
                            min="1"
                            value={seats}
                            onChange={(e) => setSeats(Math.max(1, Number(e.target.value)))}
                            className="form-control text-center"
                            style={{ maxWidth: "60px" }}
                        />
                        <button 
                            className="btn btn-outline-secondary" 
                            onClick={() => setSeats(seats + 1)}
                        >+</button>
                    </div>
                </div>

                <button 
                    className="btn btn-gradient w-100 fw-bold py-2 mt-3" 
                    onClick={handleBooking}
                    style={{
                        background: "linear-gradient(45deg, #ff416c, #ff4b2b)",
                        color: "white",
                        borderRadius: "8px",
                        border: "none"
                    }}
                >
                    🎟 Confirm Booking
                </button>

                {message && (
                    <div className="alert mt-3 text-center fw-semibold" 
                        style={{
                            background: message.includes("failed") ? "#ffcccc" : "#ccffcc",
                            color: message.includes("failed") ? "#ff0000" : "#008000"
                        }}>
                        {message}
                    </div>
                )}
            </div>
        </div>
    );
};

export default BookTicket;
