import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import { FaMapMarkerAlt, FaClock, FaChair, FaTag, FaSearch, FaTicketAlt, FaTrash } from "react-icons/fa";

const Home = () => {
    const navigate = useNavigate();
    const [shows, setShows] = useState([]);
    const [bookedShows, setBookedShows] = useState([]);
    const [activeTab, setActiveTab] = useState("available");
    const [searchTerm, setSearchTerm] = useState("");
    const [genreFilter, setGenreFilter] = useState("All");

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) {
            navigate("/login");
        } else {
            fetchShows(token);
            fetchBookedShows(token);
        }
    }, [navigate]);

    const fetchShows = async (token) => {
        try {
            const response = await axios.get("http://localhost:8090/show/getAllShows", {
                headers: { Authorization: `Bearer ${token}` },
            });
            setShows(response.data);
        } catch (error) {
            console.error("Error fetching shows:", error);
        }
    };

    const fetchBookedShows = async (token) => {
        try {
            const response = await axios.get("http://localhost:8090/book/getAllBookings", {
                headers: { Authorization: `Bearer ${token}` },
            });
            setBookedShows(response.data);
        } catch (error) {
            console.error("Error fetching booked shows:", error);
        }
    };

    const handleBookNow = (showId) => navigate(`/book/${showId}`);

    const handleCancelBooking = async (bookingId) => {
        const confirmDelete = window.confirm("Are you sure you want to cancel this booking?");
        if (!confirmDelete) return; 
    
        const token = localStorage.getItem("token");
        try {
            await axios.delete(`http://localhost:8090/book/cancelBooking/${bookingId}`, {
                headers: { Authorization: `Bearer ${token}` },
            });
    
            setBookedShows(bookedShows.filter(booking => booking.id !== bookingId));
            alert("Booking canceled successfully!");
        } catch (error) {
            console.error("Error canceling booking:", error);
            alert("Failed to cancel booking. Please try again.");
        }
    };
    
    const filteredShows = shows.filter(show =>
        (genreFilter === "All" || show.movie.genre === genreFilter) &&
        show.movie.tittle.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="container mt-4">
            <div className="d-flex justify-content-center mb-4">
                <button
                    className={`btn ${activeTab === "available" ? "btn-danger" : "btn-outline-danger"} me-2`}
                    onClick={() => setActiveTab("available")}
                >
                    🎬 Available Shows
                </button>
                <button
                    className={`btn ${activeTab === "booked" ? "btn-danger" : "btn-outline-danger"}`}
                    style={{ backgroundColor: activeTab === "booked" ? "#ff9999" : "transparent", borderColor: "#ff9999", color: activeTab === "booked" ? "white" : "#ff9999" }}
                    onClick={() => setActiveTab("booked")}
                >
                    🎟 View Booked Shows
                </button>

            </div>

            <h2 className="text-center fw-bold mb-4 text-danger">
                {activeTab === "available" ? "🎬 Available Shows" : "🎟 Your Booked Shows"}
            </h2>

            <div className="row">
                {(activeTab === "available" ? filteredShows : bookedShows).map(item => {
                    const show = activeTab === "available" ? item : item.show;
                    return (
                        <div key={show.id} className="col-lg-3 col-md-4 col-sm-6 mb-4">
                            <div className="card border-0 shadow-lg rounded-3 overflow-hidden h-100">
                                <img
                                    src={`data:${show.movie.imageType};base64,${show.movie.image}`}
                                    alt={show.movie.tittle}
                                    className="card-img-top"
                                    style={{ height: "200px", objectFit: "cover", borderRadius: "8px 8px 0 0" }}
                                />
                                <div className="card-body d-flex flex-column">
                                    <h6 className="fw-bold text-dark text-truncate">{show.movie.tittle}</h6>
                                    <p className="text-muted small mb-1">{show.movie.genre} | {show.movie.language}</p>

                                    <p className="small text-secondary mb-1">
                                        <FaMapMarkerAlt className="me-1 text-danger" />
                                        <strong>{show.theater.name}</strong> ({show.theater.location})
                                    </p>

                                    <p className="small mb-1">
                                        <FaClock className="me-1 text-primary" />
                                        <strong>Show Time:</strong> {new Date(show.dateTime).toLocaleString()}
                                    </p>

                                    <p className="small text-success d-flex align-items-center">
                                        <FaChair className="me-1 text-warning" />
                                        <strong>Seats:</strong> {activeTab === "available" ? show.availaibleSeats : item.seatsBooked}
                                        <FaTag className="ms-3 me-1 text-info" />
                                        <strong>Price:</strong> ₹{activeTab === "available" ? show.price : item.totalPrice}
                                    </p>

                                    {activeTab === "available" ? (
                                        <button
                                            className="btn w-100 fw-bold py-2 mt-auto"
                                            onClick={() => handleBookNow(show.id)}
                                            style={{
                                                background: "linear-gradient(45deg, #ff416c, #ff4b2b)",
                                                color: "white",
                                                borderRadius: "8px",
                                                border: "none",
                                                transition: "0.3s ease"
                                            }}
                                        >
                                            🎟 Book Now
                                        </button>
                                    ) : (
                                        <button
                                            className="btn btn-outline-danger w-100 fw-bold py-2 mt-auto"
                                            onClick={() => handleCancelBooking(item.id)}
                                        >
                                            <FaTrash className="me-1" /> Cancel Booking
                                        </button>
                                    )}
                                </div>
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default Home;
