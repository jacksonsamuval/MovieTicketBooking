import axios from 'axios';
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link, redirect, useNavigate } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await axios.post("http://localhost:8090/api/login", { username, password });
            localStorage.setItem("token", response.data);
            console.log("Login Successful");
            alert("Login Successful");
            setUsername("");
            setPassword("");
            navigate("/");
        } catch (error) {
            console.log("Invalid Credentials");
            alert("Invalid Credentials");
            setPassword("");
            setUsername("");
        }
    };

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="card p-4 shadow-lg" style={{ width: '350px' }}>
                <h3 className="text-center mb-4">Login</h3>
                <div className="mb-3">
                    <label className="form-label">Username</label>
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Enter username"
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input
                        type="password"
                        className="form-control"
                        placeholder="Enter password"
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button className="btn btn-danger w-100" onClick={handleLogin}>
                    Login
                </button>

                <div className="text-center mt-3">
                    <h7>Don't have Account?</h7><h7><Link to="/register" style={{ color: "red", fontWeight: "bold" }}>
                        Register Here
                    </Link></h7>
                </div>
            </div>
        </div>
    );
};

export default Login;
