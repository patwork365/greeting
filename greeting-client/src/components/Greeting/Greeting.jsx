import "./Greeting.scss";

const Greeting = (props) => {
  const {
    createdBy,
    greeting,
    originCountry,
    id
  } = props.greeting;

  // const id = props.key;

  const handleSubmit = (e) => {
    e.preventDefault()
    // console.log();
    fetch(`http://localhost:8080/greetings/${id}`, {
      method: 'DELETE',
headers: {
  'Content-Type': 'application/json'
}
// body: JSON.stringify(greeting)
})
.then((response) => response.json())
.then((json => console.log(json)))
.catch(err => console.log(err))
// e.target.reset();
}


return (
<div className="greeting-box">
  <div className = "greeting-box__greeting">
    <h3>{greeting}</h3>
    <p>Usually spoken in: {originCountry}</p>
    <p>Added by: {createdBy}</p>
</div>
<button className = "greeting-box__deleteX" onClick={handleSubmit}>X</button>
</div>
)
}

export default Greeting