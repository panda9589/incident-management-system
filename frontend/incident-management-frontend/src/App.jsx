import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import IncidentList from "./pages/IncidentList";
import AddIncident from "./pages/AddIncident";

function App() {
    return (
        <Router>
            <nav>
                <Link to="/">Home</Link>
                <Link to="/add">Report Incident</Link>
            </nav>
            <Routes>
                <Route path="/" element={<IncidentList />} />
                <Route path="/add" element={<AddIncident />} />
            </Routes>
        </Router>
    );
}

export default App;
