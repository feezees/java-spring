const usernameIdInput = document.getElementById("usernameId");
const btnInc = document.getElementById("btnInc");
const counterValue = document.getElementById("counterValue");

// btnCheckCookie

const checkCookie = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/cookie/check", {
      method: "GET",
      credentials: "include",
      headers: {
        "Content-Type": "text/plain",
        credentials: "include",
      },
    });

    const responseValue = await response.text();
    console.log(responseValue);
  } catch (error) {
    console.error("Error:", error);
  }
};

// btnSetAdminCookie
const setAdminCookie = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/cookie/admin", {
      method: "PUT",
      credentials: "include",
      headers: {
        "Content-Type": "text/plain",
      },
    });

    const responseValue = await response.text();
    console.log(responseValue);
  } catch (error) {
    console.error("Error:", error);
  }
};

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
btnCheckCookie.onclick = () => checkCookie();
btnSetAdminCookie.onclick = () => setAdminCookie();
