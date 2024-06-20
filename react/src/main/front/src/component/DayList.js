import {useEffect, useState}from "react";
import dummy from "../db/data";
import {Link} from "react-router-dom";



export default function DayList(){

    const[days, setDays] = useState([]);
    const [count, setCount] = useState(0);


    useEffect(() => {
        fetch('http://localhost:3001/days')
            .then(res=>{
                return res.json()
            })
            .then(data =>{
                setDays(data);
            })
    }, [count]); //의존성 배열.. 의존성 배열의 값이 변경되는 경우에만 함수 실행

    return (
        <>
        <ul className="list_day">
        {dummy.days.map(day=> (
            <li key={day.id}>
                <Link to={`/day/${day.day}`}>Day{day.day}</Link>
            </li>
        ))}
    </ul>
        </>
    );
}