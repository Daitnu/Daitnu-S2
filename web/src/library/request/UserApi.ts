import Api from './Api';
import { ResponseRegister, ResponseLogin } from '~/@types/response/user';
import { HTTPResponse } from '~/@types/response/success';
import { BusinessErrorResponse } from '~/@types/response/error';
import { RequestParam } from '~/@types/request/common';
import { RegisterParam, LoginParam } from '~/@types/request/user';
import URL from './url';

export default class UserApi extends Api {
  constructor() {
    super();
  }

  public async register({
    url = URL.USER.REGISTER,
    data,
  }: RequestParam<RegisterParam>): Promise<HTTPResponse<ResponseRegister> | BusinessErrorResponse> {
    return this.post<ResponseRegister, RegisterParam>({ url, data });
  }

  public async login({
    url = URL.USER.LOGIN,
    data,
  }: RequestParam<LoginParam>): Promise<HTTPResponse<ResponseLogin> | BusinessErrorResponse> {
    return this.post<ResponseLogin, LoginParam>({ url, data });
  }
}
