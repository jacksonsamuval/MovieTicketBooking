import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { FaSignInAlt, FaUserPlus, FaSignOutAlt, FaFilm } from "react-icons/fa";

const Navbar = () => {
    const navigate = useNavigate();
    const token = localStorage.getItem("token");

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/login");
    };

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-transparent shadow-sm py-3">
            <div className="container">
                <Link
                    className="navbar-brand fw-bold d-flex align-items-center"
                    to="/"
                    style={{ fontSize: "1.5rem", color: "red" }}
                >
                    <FaFilm className="me-2" style={{ color: "red" }} /> Moviezz
                </Link>

                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item">
                            <Link className="nav-link fw-semibold text-dark" to="/">Home</Link>
                        </li>

                        {!token ? (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link fw-semibold text-dark" to="/login">
                                        <FaSignInAlt className="me-1" /> Login
                                    </Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link fw-semibold text-dark" to="/register">
                                        <FaUserPlus className="me-1" /> Register
                                    </Link>
                                </li>
                            </>
                        ) : (
                            <li className="nav-item">
                                <button className="btn btn-danger text-white fw-bold px-3" onClick={handleLogout}>
                                    <FaSignOutAlt className="me-1" /> Logout
                                </button>
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
