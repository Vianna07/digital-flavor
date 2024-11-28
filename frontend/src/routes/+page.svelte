<script>

    const handleLogin = () => alert("Entrar clicado!");
    const handleSignUp = () => alert("Cadastrar clicado!");
  </script>
  
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #faf4f0;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
  
    .container {
      width: 350px;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 8px;
      background: #fff;
      text-align: center;
    }
  
    .logo img {
      width: 50px;
    }
  
    .logo h1 {
      font-size: 24px;
      margin: 10px 0;
      color: #b32a2a;
    }
  
   
  
    button {
      width: 100%;
      padding: 15px;
      margin: 10px 0;
      border-radius: 15px;
      font-size: 16px;
      cursor: pointer;
    }
  
    .btn-login {
      background-color: #b32a2a;
      color: #fff;
    }
  
    .btn-signup {
      background-color: #e0e0e0;
      color: #b32a2a;
    }

  </style>
    
  <div class="container">
    <div class="logo">
      <img src="/logo.png" alt="Digital Flavor Logo" />
      <h1>Bem-vindo ao Digital Flavor</h1>
    </div>
    <button class="primary" on:click={handleLogin}>Entrar</button>
    <button class="btn-signup" on:click={handleSignUp}>Cadastrar</button>
    <div class="divider">Continue com sua conta</div>
  </div>