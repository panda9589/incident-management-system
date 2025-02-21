import { useState, useEffect } from "react";
import axios from "axios";

function IncidentList() {
    const [incidents, setIncidents] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/incidents")
            .then(response => setIncidents(response.data))
            .catch(error => console.error("Error fetching incidents:", error));
    }, []);

    return (
        <div>
            <h2>Incident List</h2>
            <ul>
                {incidents.map(incident => (
                    <li key={incident.id}>
                        <strong>{incident.title}</strong> - {incident.status}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default IncidentList;
