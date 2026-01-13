const { useState } = React;

function App() {
    const [count, setCount] = useState(0);

    return (
        <div className="card">
            <h1>Scala + React</h1>
            <p>React is running in your portable dev environment!</p>
            <div style={{ margin: '20px' }}>
                <p>Counter: <strong>{count}</strong></p>
                <button onClick={() => setCount(count + 1)}>
                    Click Me
                </button>
            </div>
        </div>
    );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);