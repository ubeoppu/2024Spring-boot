import logo from './logo.svg';
import "./App.css"
import Header from "./component/Header";
import DayList from "./component/DayList";
import Day from "./component/Day";
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {

  return (
      <BrowserRouter>
      <div className="app">
<Header/>
          <Routes>
              <Route  path="/" element={<DayList/>}/>
              <Route  path="/day/:day" element={<Day/>}/>
          </Routes>
      </div>
      </BrowserRouter>
  );
}

export default App;
