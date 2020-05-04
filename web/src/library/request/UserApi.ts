import Api from './Api';
import { ResponseRegister } from '~/@types/response/user';
import { HTTPResponse } from '~/@types/response/success';
import { BusinessErrorResponse } from '~/@types/response/error';
import { RequestParam } from '~/@types/request/common';
import { RegisterParam } from '~/@types/request/user';
import URL from './url';

export class UserApi extends Api {
  constructor() {
    super();
  }

  public async register({
    url = URL.USER.REGISTER,
    data,
  }: RequestParam<RegisterParam>): Promise<HTTPResponse<ResponseRegister> | BusinessErrorResponse> {
    return this.post<ResponseRegister, RegisterParam>({ url, data });
  }
}
