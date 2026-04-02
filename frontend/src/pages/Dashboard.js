import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Dashboard.css';

function Dashboard() {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const handleLogout = () => {
    logout();
    navigate('/login', { replace: true });
  };

  return (
    <div className="dashboard-page">
      <div className="dashboard-card">
        <div className="dashboard-header">
          <h1>Profile</h1>
          <button onClick={handleLogout} className="logout-button">
            Log Out
          </button>
        </div>

        <div className="profile-info">
          <div className="profile-field">
            <label>Full Name</label>
            <span>{(user && user.fullName) || '—'}</span>
          </div>
          <div className="profile-field">
            <label>Email</label>
            <span>{(user && user.email) || '—'}</span>
          </div>
          <div className="profile-field">
            <label>Phone</label>
            <span>{(user && user.phone) || '—'}</span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
