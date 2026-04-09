import { Link } from 'react-router-dom';

export default function Navbar() {
  const handleLogout = () => {
    localStorage.removeItem('token');
    window.location.href = '/login';
  };

  const token = localStorage.getItem('token');

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <h1>MyApp</h1>
        <ul className="nav-links">
          <li><Link to="/">Home</Link></li>
          {token ? (
            <>
              <li><Link to="/profile">Profile</Link></li>
              <li><button onClick={handleLogout}>Logout</button></li>
            </>
          ) : (
            <>
              <li><Link to="/login">Login</Link></li>
              <li><Link to="/register">Register</Link></li>
            </>
          )}
        </ul>
      </div>
    </nav>
  );
}
