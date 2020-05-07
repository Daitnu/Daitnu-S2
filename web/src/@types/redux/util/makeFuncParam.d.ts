import { BusinessErrorResponse } from '~/@types/response/error';
import { HTTPResponse } from '~/@types/response/success';

export interface ApiCallSagaFunc {
  type: string;
  apiFunc: (...args: any[]) => Promise<HTTPResponse<any> | BusinessErrorResponse>;
  successCb?: (arg: HTTPResponse<any>) => Generator | Function;
  failCb?: (arg: BusinessErrorResponse) => Generator | Function;
}
