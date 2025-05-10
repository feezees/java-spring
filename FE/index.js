const usernameIdInput = document.getElementById("usernameId");
const btnInc = document.getElementById("btnInc");
const counterValue = document.getElementById("counterValue");

const inc = async () => {
    console.log(usernameId);
  try {
    const response = await fetch("http://localhost:8080/api/counter", {
      method: "POST",
      headers: {
        "Content-Type": "text/plain",
      },
      body: usernameIdInput.value,
    });

    const newCounterValue = await response.text();
    counterValue.innerHTML = newCounterValue;
  } catch (error) {
    console.error("Error:", error);
  }
};

inc(); // init;

btnInc.onclick = () => inc();
