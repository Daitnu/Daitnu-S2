export interface ResponseRegister {
  id: number;
  userId: string;
  email: string;
  name: string;
  subEmail: string;
}

export interface ResponseLogin {
  userId: string;
  name: string;
}
