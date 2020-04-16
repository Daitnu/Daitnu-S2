import { ResponseRegister } from './user';

interface CommonResponse {
  status: number;
  data: any;
}

interface ResponseRegisterSuccess extends CommonResponse {
  data: ResponseRegister;
}
