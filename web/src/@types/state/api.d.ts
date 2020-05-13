import { HTTPResponse } from '../response/success';
import { BusinessErrorResponse } from '../response/error';

export interface ApiState<T> {
  loading: boolean;
  data: null | HTTPResponse<T>;
  error: null | BusinessErrorResponse;
}
