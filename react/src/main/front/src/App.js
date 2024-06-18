import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <h1
        style ={{
          color:"red",
        }}
        >
        Welcome
      </h1>
        <span
        style={{
            color:"blue",
        }}>React테스트...</span>
        <p style={{
            borderStyle:"solid",
            borderRadius:"5px",
            borderWidth:"1px",
            width:"200px",
            margin:"auto",
        }}>단락 태그...</p>
    </div>

  );
}

export default App;
