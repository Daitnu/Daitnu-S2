export interface ResponseRegister {
  id: number;
  email: string;
  name: string;
  subEmail: string;
}

export interface ResponseLogin {
  userId: string;
  name: string;
}
