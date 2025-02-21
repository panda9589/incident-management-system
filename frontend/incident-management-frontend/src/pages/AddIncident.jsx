import { useState } from "react";
import axios from "axios";

function AddIncident() {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/incidents", {
            title,
            description,
            status: "OPEN"
        }).then(() => {
            alert("Incident reported successfully!");
            setTitle("");
            setDescription("");
        }).catch(error => console.error("Error reporting incident:", error));
    };

    return (
        <div>
            <h2>Report an Incident</h2>
            <form onSubmit={handleSubmit}>
                <input 
                    type="text" 
                    placeholder="Incident Title" 
                    value={title} 
                    onChange={(e) => setTitle(e.target.value)}
                    required 
                />
                <textarea 
                    placeholder="Description" 
                    value={description} 
                    onChange={(e) => setDescription(e.target.value)}
                    required 
                />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default AddIncident;