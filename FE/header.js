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
  };
};

const createLoginAdminButton = (parent) => {
  const button = document.createElement("button");
  button.className = "border-2 p-4 bg-blue-500 text-white p-2 rounded-md";
  button.id = "btnSetAdminCookie";
  button.onclick = () => setAdminCookie();
  button.innerText = 'setAdminCookie';
  parent.appendChild(button);
};

export function createHeader() {
  const header = document.createElement("header");
  header.className = "border-2";

  createLoginAdminButton(header);
  document.body.appendChild(header);

  console.log("#52");
}
