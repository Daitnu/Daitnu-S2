export interface ApiState<T> {
  loading: boolean;
  data: null | HTTPResponse<T>;
  error: null | BusinessErrorResponse;
}
